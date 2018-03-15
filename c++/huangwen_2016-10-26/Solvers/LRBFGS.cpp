
#include "LRBFGS.h"

/*Define the namespace*/
namespace ROPTLIB{

	LRBFGS::LRBFGS(const Problem *prob, const Variable *initialx)
	{
		Initialization(prob, initialx);
	};

	void LRBFGS::SetProbX(const Problem *prob, const Variable *initialx)
	{
		SolversLS::SetProbX(prob, initialx);
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

	void LRBFGS::SetDefaultParams()
	{
		SolversLS::SetDefaultParams();
		isconvex = false;
		nu = 1e-4;
		mu = 1;
		LengthSY = 4;
		S = nullptr;
		Y = nullptr;
		Currentlength = 0;
		beginidx = 0;
		RHO = nullptr;
		gamma = 1;
		InitSteptype = QUADINTMOD;
		SolverName.assign("LRBFGS");
	};

	LRBFGS::~LRBFGS(void)
	{
		delete s;
		delete y;
		DeleteVectors(S, LengthSY);
		DeleteVectors(Y, LengthSY);
		if (RHO != nullptr)
			delete[] RHO;
	};

	void LRBFGS::Run(void)
	{
		DeleteVectors(S, LengthSY);
		NewVectors(S, LengthSY);
		DeleteVectors(Y, LengthSY);
		NewVectors(Y, LengthSY);
		if (RHO != nullptr)
			delete[] RHO;
		RHO = new double[LengthSY];
		SolversLS::Run();
	};

	void LRBFGS::CheckParams(void)
	{
		SolversLS::CheckParams();
		char YES[] = "YES";
		char NO[] = "NO";
		char *status;

		std::cout << "LRBFGS METHOD PARAMETERS:" << std::endl;
		status = (nu >= 0 && nu < 1) ? YES : NO;
		std::cout << "nu            :" << std::setw(15) << nu << "[" << status << "],\t";
		status = (mu >= 0) ? YES : NO;
		std::cout << "mu            :" << std::setw(15) << mu << "[" << status << "]" << std::endl;
		status = YES;
		std::cout << "isconvex      :" << std::setw(15) << isconvex << "[" << status << "],\t";
		status = (LengthSY >= 0) ? YES : NO;
		std::cout << "LengthSY      :" << std::setw(15) << LengthSY << "[" << status << "]" << std::endl;
	};

	void LRBFGS::GetSearchDir(void)
	{
		HvLRBFGS(gf1, eta1);
		Mani->ScaleTimesVector(x1, -1.0, eta1, eta1);
	};

	void LRBFGS::UpdateData(void)
	{
		UpdateDataLRBFGS();
	};

	void LRBFGS::PrintInfo(void)
	{
		printf("\n\tbetay:%.3e,rho:%.3e,gamma:%.3e,inpss:%.3e,inpsy:%.3e,IsUpdateHessian:%d,", betay, rho, gamma, inpss, inpsy, isupdated);
		printf("\n");
	};
}; /*end of ROPTLIB namespace*/
