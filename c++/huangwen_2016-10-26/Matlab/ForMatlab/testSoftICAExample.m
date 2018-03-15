function [FinalX, fv, gfv, gfgf0, iter, nf, ng, nR, nV, nVp, nH, ComTime, funs, grads, times] = testSimpleExample()
    n = 3;
    p = 2;
    num = 100;
    
    rand('twister', 2);
    C = rand(n, n, num);
    for i = 1 : num
        C(:, :, i) = C(:, :, i) + C(:, :, i)';
    end
    tmpx0 = rand(n, p);
    [x0, ~] = qr(tmpx0, 0);
    
    fhandle = @(x)f(x, C);
    gfhandle = @(x)gf(x, C);
    Hesshandle = @(x, eta)Hess(x, eta, C);

    SolverParams.method = 'RSD';
    % SolverParams.IsCheckParams = 1;
    SolverParams.DEBUG = 1;
    % SolverParams.Min_Iteration = 100;
    SolverParams.IsCheckGradHess = 0;
    SolverParams.Max_Iteration = 500;
    SolverParams.OutputGap = 1;
    SolverParams.IsStopped = @IsStopped;
    
    SolverParams.LineSearch_LS = 0;
    SolverParams.LS_ratio1 = 0.5;
    SolverParams.LS_ratio2 = 0.5;

    % ManiParams.IsCheckParams = 1;
    ManiParams.name = 'Stiefel';
    ManiParams.n = n;
    ManiParams.p = p;
    ManiParams.ParamSet = 1;
    HasHHR = 0;

    initialX.main = x0;

    [FinalX, fv, gfv, gfgf0, iter, nf, ng, nR, nV, nVp, nH, ComTime, funs, grads, times] = DriverOPT(fhandle, gfhandle, Hesshandle, SolverParams, ManiParams, HasHHR, initialX);
end

function output = IsStopped(x, gf, f, ngf, ngf0)
    output = ngf < 1e-3;
end

function [output, x] = f(x, C)
    [n, p] = size(x.main);
    num = size(C, 3);
    x.CY = zeros(n, p, num);
    x.D = zeros(p * num, 1);
    for i = 1 : num
        x.CY(:, :, i) = C(:, :, i) * x.main;
        for j = 1 : p
            x.D(j + i * p) = x.main(:, j)' * x.CY(:, j, i);
        end
    end
    output = - norm(x.D)^2;
end

function [output, x] = gf(x, C)
    num = size(C, 3);
    [n, p] = size(x.main);
    output.main = zeros(n, p);
    for i = 1 : num
        for j = 1 : p
            output.main(:, j) = output.main(:, j) - 4 * x.D(j + i * p) * x.CY(:, j, i);
        end
    end
end

function [output, x] = Hess(x, eta, C)
    output.main = eta.main;
end
