
#include "LRBFGSLPSub.h"

/*Define the namespace*/
namespace ROPTLIB{

	void LRBFGSLPSub::Run(void)
	{
		DeleteVectors(S, LengthSY);
		NewVectors(S, LengthSY);
		DeleteVectors(Y, LengthSY);
		NewVectors(Y, LengthSY);
		if (RHO != nullptr)
			delete[] RHO;
		RHO = new double[LengthSY];

		SolversLSLPSub::Run();
	};

	LRBFGSLPSub::LRBFGSLPSub(const Problem *prob, const Variable *initialx)
	{
		Initialization(prob, initialx);
	};

	void LRBFGSLPSub::Initialization(const Problem *prob, const Variable *initialx)
	{
		SetProbX(prob, initialx);
		SetDefaultParams();
	};

	void LRBFGSLPSub::SetProbX(const Problem *prob, const Variable *initialx)
	{
		SolversLSLPSub::SetProbX(prob, initialx);

		const Vector *EMPTYETA;
		if (prob->GetDomain()->GetIsIntrinsic())
			EMPTYETA = prob->GetDomain()->GetEMPTYINTR();
		else
			EMPTYETA = prob->GetDomain()->GetEMPTYEXTR();

		s = EMPTYETA->ConstructEmpty();
		y = EMPTYETA->ConstructEmpty();

		prob->SetUseGrad(true);
		prob->SetUseHess(false);
	};

	void LRBFGSLPSub::SetDefaultParams()
	{
		SolversLSLPSub::SetDefaultParams();
		Hv = &QuasiNewton::HvLRBFGSSub;
		isconvex = false;
		InitSteptype = ONESTEP;
		LineSearch_LS = WOLFELP;
		lambdaLower = 1e-2;//-- 7;
		lambdaUpper = 1e2;//-- 7;
		LengthSY = 2;
		S = nullptr;
		Y = nullptr;
		RHO = nullptr;
		Currentlength = 0;
		beginidx = 0;
		gamma = 1;
		SolversLSLPSub::SolverName.assign("LRBFGSLPSub");
	};

	LRBFGSLPSub::~LRBFGSLPSub(void)
	{
		delete s;
		delete y;
		DeleteVectors(S, LengthSY);
		DeleteVectors(Y, LengthSY);
		if (RHO != nullptr)
			delete[] RHO;
	};

	void LRBFGSLPSub::PrintInfo(void)
	{
		printf("\n\tbetay:%.3e,inpss:%.3e,inpsy:%.3e,inpyy:%.3e,IsUpdateHessian:%d,", betay, inpss, inpsy, inpyy, isupdated);
		printf("\n");
	};

	void LRBFGSLPSub::CheckParams(void)
	{
		SolversLSLPSub::CheckParams();
		char YES[] = "YES";
		char NO[] = "NO";
		char *status;

		std::cout << "LRBFGSLPSub METHOD PARAMETERS:" << std::endl;
		status = (lambdaLower > 0 && lambdaLower < lambdaUpper) ? YES : NO;
		std::cout << "lambdaLower   :" << std::setw(15) << lambdaLower << "[" << status << "],\t";
		status = (lambdaUpper >= lambdaLower) ? YES : NO;
		std::cout << "lambdaUpper   :" << std::setw(15) << lambdaUpper << "[" << status << "]" << std::endl;
		status = YES;
		std::cout << "isconvex      :" << std::setw(15) << isconvex << "[" << status << "],\t";
		status = (LengthSY >= 0) ? YES : NO;
		std::cout << "LengthSY      :" << std::setw(15) << LengthSY << "[" << status << "]" << std::endl;
	};

	void LRBFGSLPSub::UpdateData(void)
	{
		gf->CopyTo(gf1);
		UpdateDataLRBFGSSub();
	};
}; /*end of ROPTLIB namespace*/
