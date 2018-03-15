function MTestStieBrockett()
    r = rand() * 100000;
    r = 1
    fprintf('seed:%d\n', r);
    rand('state', r);
    randn('state', r);
    n = 1000;
    p = 4;
    Xinitial = orth(randn(n, p));
    B = randn(n, n);
    B = B + B';
    
    D = ones(p, 1);%(p:-1:1)'; %
    SolverParams.method = 'LRBFGS';
%     SolverParams.method = 'RTRSR1';
%     SolverParams.method = 'RTRNewton';
    SolverParams.IsCheckParams = 1;
    SolverParams.Max_Iteration = 2000;
    SolverParams.LengthSY = 4;
    SolverParams.DEBUG = 1;
    SolverParams.Accuracy = 1e-6;
    SolverParams.Tolerance = 1e-6;% * norm(B);
    SolverParams.Finalstepsize = 1;
    HasHHR = 0;
    [Xopt, f, gf, gfgf0, iter, nf, ng, nR, nV, nVp, nH, ComTime] = TestStieBrockett(B, D, Xinitial, HasHHR, 1, SolverParams);
end
