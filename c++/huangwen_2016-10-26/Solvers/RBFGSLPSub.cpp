
#include "RBFGSLPSub.h"

/*Define the namespace*/
namespace ROPTLIB{

	RBFGSLPSub::RBFGSLPSub(const Problem *prob, const Variable *initialx, LinearOPE *initialH)
	{
		Initialization(prob, initialx, initialH);
	};

	void RBFGSLPSub::Initialization(const Problem *prob, const Variable *initialx, LinearOPE *initialH)
	{
		SetProbX(prob, initialx, initialH);
		SetDefaultParams();
	};

	void RBFGSLPSub::SetProbX(const Problem *prob, const Variable *initialx, LinearOPE *initialH)
	{
		SolversLSLPSub::SetProbX(prob, initialx);

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

	void RBFGSLPSub::SetDefaultParams()
	{
		SolversLSLPSub::SetDefaultParams();
		isconvex = false;
		LineSearch_LS = WOLFELP;
		InitSteptype = ONESTEP;
		lambdaLower = 1e-2;//-- 7;
		lambdaUpper = 1e2;//-- 7;
		Hv = &QuasiNewton::HvRBFGSSub;
		SolversLSLPSub::SolverName.assign("RBFGSLPSub");
	};

	RBFGSLPSub::~RBFGSLPSub(void)
	{
		delete s;
		delete y;
		delete H;
		delete tildeH;
	};

	void RBFGSLPSub::PrintInfo(void)
	{
		printf("\n\tbetay:%.3e,inpss:%.3e,inpsy:%.3e,inpyy:%.3e,IsUpdateHessian:%d,", betay, inpss, inpsy, inpyy, isupdated);
		printf("\n");
	};

	void RBFGSLPSub::CheckParams(void)
	{
		SolversLSLPSub::CheckParams();
		char YES[] = "YES";
		char NO[] = "NO";
		char *status;

		std::cout << "RBFGSLPSub METHOD PARAMETERS:" << std::endl;
		status = (lambdaLower > 0 && lambdaLower < lambdaUpper) ? YES : NO;
		std::cout << "lambdaLower   :" << std::setw(15) << lambdaLower << "[" << status << "],\t";
		status = (lambdaUpper >= lambdaLower) ? YES : NO;
		std::cout << "lambdaUpper   :" << std::setw(15) << lambdaUpper << "[" << status << "]" << std::endl;
		status = YES;
		std::cout << "isconvex      :" << std::setw(15) << isconvex << "[" << status << "]" << std::endl;
	};

	void RBFGSLPSub::UpdateData(void)
	{
		gf->CopyTo(gf1);
		UpdateDataRBFGSSub();
	};
}; /*end of ROPTLIB namespace*/
