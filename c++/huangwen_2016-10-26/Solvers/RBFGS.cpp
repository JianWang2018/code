
#include "RBFGS.h"

/*Define the namespace*/
namespace ROPTLIB{

	RBFGS::RBFGS(const Problem *prob, const Variable *initialx, LinearOPE *initialH)
	{
		Initialization(prob, initialx, initialH);
	};

	void RBFGS::Initialization(const Problem *prob, const Variable *initialx, LinearOPE *initialH)
	{
		SetProbX(prob, initialx, initialH);
		SetDefaultParams();
	};

	void RBFGS::SetProbX(const Problem *prob, const Variable *initialx, LinearOPE *initialH)
	{
		SolversLS::SetProbX(prob, initialx);

		const Vector *EMPTYETA;
		if (prob->GetDomain()->GetIsIntrinsic())
			EMPTYETA = prob->GetDomain()->GetEMPTYINTR();
		else
			EMPTYETA = prob->GetDomain()->GetEMPTYEXTR();
		bool initHisnull = (initialH == nullptr);
		if (initHisnull)
		{
			if (prob->GetDomain()->GetIsIntrinsic())
			{
				initialH = new LinearOPE(prob->GetDomain()->GetEMPTYINTR()->Getlength());
			}
			else
			{
				initialH = new LinearOPE(prob->GetDomain()->GetEMPTYEXTR()->Getlength());
			}
			initialH->ScaledIdOPE();
		}

		H = initialH->ConstructEmpty();
		tildeH = initialH->ConstructEmpty();
		initialH->CopyTo(H);
		s = EMPTYETA->ConstructEmpty();
		y = EMPTYETA->ConstructEmpty();

		if (initHisnull)
			delete initialH;
		prob->SetUseGrad(true);
		prob->SetUseHess(false);
	};

	void RBFGS::SetDefaultParams()
	{
		SolversLS::SetDefaultParams();
		isconvex = false;
		nu = 1e-4;
		mu = 1;
		InitSteptype = QUADINTMOD;
		SolversLS::SolverName.assign("RBFGS");
	};

	RBFGS::~RBFGS(void)
	{
		delete s;
		delete y;
		delete H;
		delete tildeH;
	};

	void RBFGS::CheckParams(void)
	{
		SolversLS::CheckParams();
		char YES[] = "YES";
		char NO[] = "NO";
		char *status;

		std::cout << "RBFGS METHOD PARAMETERS:" << std::endl;
		status = (nu >= 0 && nu < 1) ? YES : NO;
		std::cout << "nu            :" << std::setw(15) << nu << "[" << status << "],\t";
		status = (mu >= 0) ? YES : NO;
		std::cout << "mu            :" << std::setw(15) << mu << "[" << status << "]" << std::endl;
		status = YES;
		std::cout << "isconvex      :" << std::setw(15) << isconvex << "[" << status << "]" << std::endl;
	};

	void RBFGS::GetSearchDir(void)
	{
		HvRBFGS(gf1, eta1);
		Mani->ScaleTimesVector(x1, -1.0, eta1, eta1);
	};

	void RBFGS::UpdateData(void)
	{
		UpdateDataRBFGS();
	};

	void RBFGS::PrintInfo(void)
	{
		printf("\n\tbetay:%.3e,inpss:%.3e,inpsy:%.3e,IsUpdateHessian:%d,", betay, inpss, inpsy, isupdated);
		printf("\n");
	};

}; /*end of ROPTLIB namespace*/
