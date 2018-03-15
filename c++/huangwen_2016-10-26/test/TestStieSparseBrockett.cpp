
#include "TestStieSparseBrockett.h"

using namespace ROPTLIB;

/*If the file is not compiled in Matlab and TESTSTIEBROCKETT is defined in def.h file, then using the following
main() function as the entrance. */
#if !defined(MATLAB_MEX_FILE) && defined(TESTSTIESPARSEBROCKETT)

/*Help to check the memory leakage problem. No necesary any more.*/
std::map<integer *, integer> *CheckMemoryDeleted;

int main(void)
{
	/*Set the random seed*/
	unsigned tt = (unsigned)time(NULL);
	genrandseed(tt);

	// size of the Stiefel manifold
	integer n = 4, p = 2, nzmax = 7;

	// Generate the matrices in the Brockett problem.
	double *B = new double[nzmax + p];
	double *D = B + nzmax;
	unsigned long long *ir = new unsigned long long[nzmax];
	unsigned long long *jc = new unsigned long long[n + 1];
	/*B is an n by n sparse symmetric matrix with nzmax nonzero entries*/

	B[0] = genrandnormal();
	B[1] = genrandnormal();
	B[2] = genrandnormal();
	B[3] = genrandnormal();
	B[4] = B[1];
	B[5] = B[2];
	B[6] = B[3];
	jc[0] = 0; jc[1] = 2; jc[2] = 4; jc[3] = 6; jc[4] = 7;
	ir[0] = 0; ir[1] = 2; ir[2] = 2; ir[3] = 3; ir[4] = 0; ir[5] = 1; ir[6] = 1;

	/*D is a diagonal matrix.*/
	for (integer i = 0; i < p; i++)
		D[i] = static_cast<double> (i + 1);

	CheckMemoryDeleted = new std::map<integer *, integer>;
	testStieSparseBrockett(B, ir, jc, nzmax, D, n, p);
	std::map<integer *, integer>::iterator iter = CheckMemoryDeleted->begin();
	for (iter = CheckMemoryDeleted->begin(); iter != CheckMemoryDeleted->end(); iter++)
	{
		if (iter->second != 1)
			std::cout << "Global address:" << iter->first << ", sharedtimes:" << iter->second << std::endl;
	}
	delete CheckMemoryDeleted;
	delete[] B;
#ifdef _WIN64
#ifdef _DEBUG
	_CrtDumpMemoryLeaks();
#endif
#endif
	return 0;
}

/*We don't have to a line search algorithm defined in the solvers. The line seach algorithm can be defined
here:*/
double LinesearchInput(Variable *x1, Vector *eta1, double initialstepsize, double initialslope, const Problem *prob)
{ /*For example, simply use one to be the stepsize*/

	const StieSparseBrockett *P = dynamic_cast<StieSparseBrockett *> (const_cast<Problem*> (prob));
	const double *etaTV = eta1->ObtainReadData();
	const double *xM = x1->ObtainReadData();

	integer n = P->n, p = P->p, length = n * p;
	double denor, nume;
	double *B = P->B, *D = P->D;
	unsigned long long *ir = P->ir, *jc = P->jc;
	integer nzmax = P->nzmax;
	double *tmp = new double[n * p];

	Matrix::SPBtimesX(B, ir, jc, nzmax, etaTV, n, p, tmp);
	
	denor = ddot_(&length, tmp, &GLOBAL::IONE, const_cast<double *> (etaTV), &GLOBAL::IONE);
	nume = ddot_(&length, tmp, &GLOBAL::IONE, const_cast<double *> (xM), &GLOBAL::IONE);

	delete[] tmp;
	return (-nume / denor < 0) ? 1 : -nume / denor;
}

void testStieSparseBrockett(double *B, unsigned long long *ir, unsigned long long *jc, integer nzmax, double *D, integer n, integer p, double *X, double *Xopt)
{
	// choose a random seed
	unsigned tt = (unsigned)time(NULL);
	tt = 0;
	genrandseed(tt);
	StieVariable StieX(n, p);

	if (X == nullptr)
	{/*If X is not defined before, then obtain an initial iterate by taking the Q factor of qr decomposition*/
		StieX.RandInManifold();
	}
	else
	{/*Otherwise, using the input orthonormal matrix as the initial iterate*/
		double *StieXptr = StieX.ObtainWriteEntireData();
		for (integer i = 0; i < n * p; i++)
			StieXptr[i] = X[i];
	}

	// Define the manifold
	Stiefel Domain(n, p);
	//Domain.SetHasHHR(true); /*set whether the manifold uses the idea in [HGA2015, Section 4.3] or not*/

	// Define the Brockett problem
	StieSparseBrockett Prob(B, ir, jc, nzmax, D, n, p);
	/*The domain of the problem is a Stiefel manifold*/
	Prob.SetDomain(&Domain);

	/*Output the parameters of the domain manifold*/
	Domain.CheckParams();

	//Prob.CheckGradHessian(&StieX);

	LRBFGS *LRBFGSsolver = new LRBFGS(&Prob, &StieX);
	LRBFGSsolver->LineSearch_LS = ARMIJO; //INPUTFUN;//  
	LRBFGSsolver->LinesearchInput = &LinesearchInput;
	LRBFGSsolver->Debug = ITERRESULT; //ITERRESULT;// 
	LRBFGSsolver->InitSteptype = QUADINTMOD;
	LRBFGSsolver->OutputGap = 1;
	LRBFGSsolver->LengthSY = 4;
	LRBFGSsolver->Max_Iteration = 1000;
	LRBFGSsolver->CheckParams();
	LRBFGSsolver->Run();
	//Prob.CheckGradHessian(LRBFGSsolver->GetXopt());
	delete LRBFGSsolver;

	//RSD *RSDsolver = new RSD(&Prob, &StieX);//----
	//RSDsolver->LineSearch_LS = ARMIJO;
	//RSDsolver->Debug = FINALRESULT;
	//RSDsolver->InitSteptype = BBSTEP;
	//RSDsolver->OutputGap = 10;
	//RSDsolver->Max_Iteration = 2000;
	//RSDsolver->CheckParams();
	//RSDsolver->Run();
	//delete RSDsolver;

	//RCG *RCGsolver = new RCG(&Prob, &StieX);
	//RCGsolver->RCGmethod = POLAK_RIBIERE_MOD;
	//RCGsolver->Debug = FINALRESULT;
	//RCGsolver->OutputGap = 10;
	//RCGsolver->LineSearch_LS = ARMIJO;
	//RCGsolver->InitSteptype = BBSTEP;
	//RCGsolver->Max_Iteration = 2000;
	//RCGsolver->CheckParams();
	//RCGsolver->Run();
	//delete RCGsolver;

	return;//--------------

	/*Check the correctness of the manifold operations*/
	Domain.CheckIntrExtr(&StieX);
	Domain.CheckRetraction(&StieX);
	Domain.CheckDiffRetraction(&StieX);
	Domain.CheckLockingCondition(&StieX);
	Domain.CheckcoTangentVector(&StieX);
	Domain.CheckIsometryofVectorTransport(&StieX);
	Domain.CheckIsometryofInvVectorTransport(&StieX);
	Domain.CheckVecTranComposeInverseVecTran(&StieX);
	Domain.CheckTranHInvTran(&StieX);
	Domain.CheckHaddScaledRank1OPE(&StieX);

	// test RSD
	std::cout << "********************************Check all line search algorithm in RSD*****************************************" << std::endl;
	for (integer i = 0; i < LSALGOLENGTH; i++)
	{
		RSD *RSDsolver = new RSD(&Prob, &StieX);
		RSDsolver->LineSearch_LS = static_cast<LSAlgo> (i);
		RSDsolver->Debug = FINALRESULT;
		RSDsolver->Max_Iteration = 2000;
		RSDsolver->CheckParams();
		RSDsolver->Run();
		delete RSDsolver;
	}

	// test RNewton
	std::cout << "********************************Check all line search algorithm in RNewton*************************************" << std::endl;
	for (integer i = 0; i < 1; i++)
	{
		RNewton *RNewtonsolver = new RNewton(&Prob, &StieX);
		RNewtonsolver->LineSearch_LS = static_cast<LSAlgo> (i);
		RNewtonsolver->Debug = ITERRESULT;
		/*Uncomment following two lines to use the linesearch algorithm defined by the function "LinesearchInput".*/
		//RNewtonsolver->LineSearch_LS = INPUTFUN;
		//RNewtonsolver->LinesearchInput = &LinesearchInput;
		RNewtonsolver->Max_Iteration = 10;
		RNewtonsolver->CheckParams();
		RNewtonsolver->Run();
		delete RNewtonsolver;
	}

	// test RCG
	std::cout << "********************************Check all Formulas in RCG*************************************" << std::endl;
	for (integer i = 0; i < RCGMETHODSLENGTH; i++)
	{
		RCG *RCGsolver = new RCG(&Prob, &StieX);
		RCGsolver->RCGmethod = static_cast<RCGmethods> (i);
		RCGsolver->LineSearch_LS = ARMIJO;
		RCGsolver->LS_beta = 0.1;
		RCGsolver->Debug = FINALRESULT;
		RCGsolver->CheckParams();
		RCGsolver->Run();
		delete RCGsolver;
	}

	// test RBroydenFamily
	std::cout << "********************************Check all line search algorithm in RBroydenFamily*************************************" << std::endl;
	for (integer i = 0; i < LSALGOLENGTH; i++)
	{
		RBroydenFamily *RBroydenFamilysolver = new RBroydenFamily(&Prob, &StieX);
		RBroydenFamilysolver->LineSearch_LS = static_cast<LSAlgo> (i);
		RBroydenFamilysolver->Debug = FINALRESULT;
		RBroydenFamilysolver->CheckParams();
		RBroydenFamilysolver->Run();
		delete RBroydenFamilysolver;
	}

	// test RWRBFGS
	std::cout << "********************************Check all line search algorithm in RWRBFGS*************************************" << std::endl;
	for (integer i = 0; i < LSALGOLENGTH; i++)
	{
		RWRBFGS *RWRBFGSsolver = new RWRBFGS(&Prob, &StieX);
		RWRBFGSsolver->LineSearch_LS = static_cast<LSAlgo> (i);
		RWRBFGSsolver->Debug = FINALRESULT; //ITERRESULT;//
		RWRBFGSsolver->CheckParams();
		RWRBFGSsolver->Run();
		delete RWRBFGSsolver;
	}

	// test RBFGS
	std::cout << "********************************Check all line search algorithm in RBFGS*************************************" << std::endl;
	for (integer i = 0; i < LSALGOLENGTH; i++)
	{
		RBFGS *RBFGSsolver = new RBFGS(&Prob, &StieX);
		RBFGSsolver->LineSearch_LS = static_cast<LSAlgo> (i);
		RBFGSsolver->Debug = FINALRESULT;
		RBFGSsolver->CheckParams();
		RBFGSsolver->Run();
		delete RBFGSsolver;
	}

	// test LRBFGS
	std::cout << "********************************Check all line search algorithm in LRBFGS*************************************" << std::endl;
	for (integer i = 0; i < LSALGOLENGTH; i++)
	{
		LRBFGS *LRBFGSsolver = new LRBFGS(&Prob, &StieX);
		LRBFGSsolver->LineSearch_LS = static_cast<LSAlgo> (i);
		LRBFGSsolver->Debug = FINALRESULT; //ITERRESULT;// 
		LRBFGSsolver->CheckParams();
		LRBFGSsolver->Run();
		delete LRBFGSsolver;
	}

	// test RTRSD
	std::cout << "********************************Check RTRSD*************************************" << std::endl;
	RTRSD RTRSDsolver(&Prob, &StieX);
	RTRSDsolver.Debug = FINALRESULT;
	RTRSDsolver.Max_Iteration = 5000;
	RTRSDsolver.CheckParams();
	RTRSDsolver.Run();

	// test RTRNewton
	std::cout << "********************************Check RTRNewton*************************************" << std::endl;
	RTRNewton RTRNewtonsolver(&Prob, &StieX);
	RTRNewtonsolver.Debug = FINALRESULT;
	RTRNewtonsolver.CheckParams();
	RTRNewtonsolver.Run();

	// test RTRSR1
	std::cout << "********************************Check RTRSR1*************************************" << std::endl;
	RTRSR1 RTRSR1solver(&Prob, &StieX);
	RTRSR1solver.Debug = FINALRESULT;
	RTRSR1solver.CheckParams();
	RTRSR1solver.Run();

	// test LRTRSR1
	std::cout << "********************************Check LRTRSR1*************************************" << std::endl;
	LRTRSR1 LRTRSR1solver(&Prob, &StieX);
	LRTRSR1solver.Debug = FINALRESULT;
	LRTRSR1solver.CheckParams();
	LRTRSR1solver.Run();

	// Check the correctness of gradient and Hessian at the initial iterate
	Prob.CheckGradHessian(&StieX);
	const Variable *xopt = RTRNewtonsolver.GetXopt();
	// Check the correctness of gradient and Hessian at the final iterate of RTRNewton method
	Prob.CheckGradHessian(xopt);

	//Output the optimizer obtained by RTRNewton method
	if (Xopt != nullptr)
	{
		const double *xoptptr = xopt->ObtainReadData();
		for (integer i = 0; i < n * p; i++)
			Xopt[i] = xoptptr[i];
	}
}

#endif

/*If it is compiled in Matlab, then the following "mexFunction" is used as the entrance.*/
#ifdef MATLAB_MEX_FILE

/*Help to check the memory leakage problem. No necesary any more.*/
std::map<integer *, integer> *CheckMemoryDeleted;

/*This function checks the number and formats of input parameters.
nlhs: the number of output in mxArray format
plhs: the output objects in mxArray format
nrhs: the number of input in mxArray format
prhs: the input objects in mxArray format */
void mexFunction(int nlhs, mxArray *plhs[], int nrhs, const mxArray *prhs[])
{
	if (nrhs < 6)
	{
		mexErrMsgTxt("The number of arguments should be at least six.\n");
	}
	double *B, *D, *X, *Xopt;
	B = mxGetPr(prhs[0]);
	D = mxGetPr(prhs[1]);
	X = mxGetPr(prhs[2]);
	/* dimensions of input matrices */
	integer p, n, HasHHR, Paramset, nzmax;
	unsigned long long *ir, *jc;
	nzmax = mxGetNzmax(prhs[0]);
	ir = mxGetIr(prhs[0]);
	jc = mxGetJc(prhs[0]);
	n = mxGetM(prhs[0]);
	p = mxGetM(prhs[1]);

	/*Check the correctness of the inputs*/
	if (mxGetN(prhs[0]) != n)
	{
		mexErrMsgTxt("The size of matrix B is not correct.\n");
	}
	if (mxGetN(prhs[1]) != 1)
	{
		mexErrMsgTxt("The size of the D is not correct!\n");
	}
	if (mxGetM(prhs[2]) != n || mxGetN(prhs[2]) != p)
	{
		mexErrMsgTxt("The size of the initial X is not correct!\n");
	}
	HasHHR = static_cast<integer> (mxGetScalar(prhs[3]));
	Paramset = static_cast<integer> (mxGetScalar(prhs[4]));

	std::cout << "(n, p):" << n << "," << p << std::endl;

	///*create output matrix*/
	//plhs[0] = mxCreateDoubleMatrix(n, p, mxREAL);
	//Xopt = mxGetPr(plhs[0]);

	genrandseed(0);

	CheckMemoryDeleted = new std::map<integer *, integer>;

	// Obtain an initial iterate by taking the Q factor of qr decomposition
	StieVariable StieX(n, p);
	double *StieXptr = StieX.ObtainWriteEntireData();
	for (integer i = 0; i < n * p; i++)
		StieXptr[i] = X[i];

	// Define the manifold
	Stiefel Domain(n, p);
	if (Paramset == 1)
		Domain.ChooseStieParamsSet1();
	else if (Paramset == 2)
		Domain.ChooseStieParamsSet2();
	else if (Paramset == 3)
		Domain.ChooseStieParamsSet3();

	// Define the Brockett problem
	StieSparseBrockett Prob(B, ir, jc, nzmax, D, n, p);
	Prob.SetDomain(&Domain);

	Domain.SetHasHHR(HasHHR != 0);
	//Domain.CheckParams();

	// Call the function defined in DriverMexProb.h
	ParseSolverParamsAndOptimizing(prhs[5], &Prob, &StieX, plhs);

	std::map<integer *, integer>::iterator iter = CheckMemoryDeleted->begin();
	for (iter = CheckMemoryDeleted->begin(); iter != CheckMemoryDeleted->end(); iter++)
	{
		if (iter->second != 1)
			std::cout << "Global address:" << iter->first << ", sharedtimes:" << iter->second << std::endl;
	}
	delete CheckMemoryDeleted;
	return;
}

#endif
