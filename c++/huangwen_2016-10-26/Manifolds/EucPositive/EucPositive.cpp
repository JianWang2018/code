
#include "EucPositive.h"

/*Define the namespace*/
namespace ROPTLIB{

	EucPositive::EucPositive(integer r, integer c, integer n)
	{
		row = r;
		col = c;
		num = n;
		IsIntrApproach = false;
		HasHHR = false;
		UpdBetaAlone = false;
		name.assign("EucPostive");
		IntrinsicDim = n * r * c;
		ExtrinsicDim = n * r * c;
		EMPTYEXTR = new EucPosVector(r, c, n);
		EMPTYINTR = new EucPosVector(r, c, n);
	};

	EucPositive::~EucPositive(void)
	{
		delete EMPTYEXTR;
		delete EMPTYINTR;
	};

	void EucPositive::CheckParams(void) const
	{
		Manifold::CheckParams();
		std::cout << name << " PARAMETERS:" << std::endl;
		if (col == 1 && num == 1)
			std::cout << "row           :" << std::setw(15) << row << std::endl;
		else
		if (num == 1)
		{
			std::cout << "row           :" << std::setw(15) << row << ",\t";
			std::cout << "col           :" << std::setw(15) << col << std::endl;
		}
		else
		{
			std::cout << "row           :" << std::setw(15) << row << ",\t";
			std::cout << "col           :" << std::setw(15) << col << std::endl;
			std::cout << "num           :" << std::setw(15) << num << std::endl;
		}
	};

	void EucPositive::Projection(Variable *x, Vector *etax, Vector *result) const
	{
		const double *etaxTV = etax->ObtainReadData();
		const double *xM = x->ObtainReadData();
		double *resultptr = result->ObtainWriteEntireData();
		for (integer i = 0; i < result->Getlength(); i++)
		{
			resultptr[i] = (xM[i] == 0 && etaxTV[i] < 0) ? 0 : etaxTV[i];
		}
	};

	void EucPositive::Retraction(Variable *x, Vector *etax, Variable *result) const
	{
		Manifold::Retraction(x, etax, result);
		double *resultptr = result->ObtainWritePartialData();
		for (integer i = 0; i < result->Getlength(); i++)
		{
			resultptr[i] = (resultptr[i] < 0) ? 0 : resultptr[i];
		}
	};

	void EucPositive::EucGradToGrad(Variable *x, Vector *egf, Vector *gf, const Problem *prob) const
	{
		const double *xM = x->ObtainReadData();
		const double *egfTV = egf->ObtainReadData();
		double *gfTV = gf->ObtainWriteEntireData();
		for (integer i = 0; i < gf->Getlength(); i++)
		{
			gfTV[i] = (xM[i] == 0 && egfTV[i] > 0) ? 0 : gfTV[i];
		}
	};

	void EucPositive::EucHvToHv(Variable *x, Vector *etax, Vector *exix, Vector* xix, const Problem *prob) const
	{
		exix->CopyTo(xix);
	};
}; /*end of ROPTLIB namespace*/
