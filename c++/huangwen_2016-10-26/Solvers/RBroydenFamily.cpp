
#include "RBroydenFamily.h"

/*Define the namespace*/
namespace ROPTLIB{

	RBroydenFamily::RBroydenFamily(const Problem *prob, const Variable *initialx, LinearOPE *initialH)
	{
		Initialization(prob, initialx, initialH);
	};

	void RBroydenFamily::Initialization(const Problem *prob, const Variable *initialx, LinearOPE *initialH)
	{
		SetProbX(prob, initialx, initialH);
		SetDefaultParams();
	};

	void RBroydenFamily::SetProbX(const Problem *prob, const Variable *initialx, LinearOPE *initialH)
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
		u = EMPTYETA->ConstructEmpty();
		if (initHisnull)
			delete initialH;
		prob->SetUseGrad(true);
		prob->SetUseHess(false);
	};

	void RBroydenFamily::SetDefaultParams()
	{
		SolversLS::SetDefaultParams();
		isconvex = false;
		nu = 1e-4;
		mu = 1;
		InitSteptype = QUADINTMOD;
		SolverName.assign("RBroydenFamily");
	};

	RBroydenFamily::~RBroydenFamily(void)
	{
		delete s;
		delete y;
		delete u;
		delete H;
		delete tildeH;
	};

	void RBroydenFamily::CheckParams(void)
	{
		SolversLS::CheckParams();
		char YES[] = "YES";
		char NO[] = "NO";
		char *status;

		std::cout << "RBROYDENFAMILY METHOD PARAMETERS:" << std::endl;
		status = (nu >= 0 && nu < 1) ? YES : NO;
		std::cout << "nu            :" << std::setw(15) << nu << "[" << status << "],\t";
		status = (mu >= 0) ? YES : NO;
		std::cout << "mu            :" << std::setw(15) << mu << "[" << status << "]" << std::endl;
		status = YES;
		std::cout << "isconvex      :" << std::setw(15) << isconvex << "[" << status << "]" << std::endl;
	};

	void RBroydenFamily::GetSearchDir(void)
	{
		HvRBroydenFamily(gf1, eta1);
		Mani->ScaleTimesVector(x1, -1.0, eta1, eta1);
	};

	void RBroydenFamily::UpdateData(void)
	{
		UpdateDataRBroydenFamily();
	};

	void RBroydenFamily::PrintInfo(void)
	{
		printf("\n\tbetay:%.3e,Phic:%.3e,inpss:%.3e,inpsy:%.3e,IsUpdateHessian:%d,", betay, phic, inpss, inpsy, isupdated);
		printf("\n");
	};
}; /*end of ROPTLIB namespace*/
