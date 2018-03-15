function [newC2] = test(C1, C2, w)
    if(nargin == 2)
        w = 0.01;
    end

    % turn on or off the option of plotting figure.
    isplot = 0;
    
    % gaurantee the first and the last points in the curves are the same.
%     C1 = [C1; C1(1, :)];
%     C2 = [C2; C2(1, :)];
    
    if(isplot == 1)
        set(0,'defaultaxesfontsize',14, ...
           'defaultaxeslinewidth',0.7, ...
           'defaultlinelinewidth',.8,'defaultpatchlinewidth',0.8);
        set(0,'defaultlinemarkersize',10)

        h1 = figure(1);clf
        C1plot = C1;
        C2plot = C2;
        subplot(1, 3, 1);
        plot(C1plot(:, 1), C1plot(:, 2), '.b-')
        hold on
        plot(C2plot(:, 1), C2plot(:, 2), '.r-')
        legend('\beta_1','\beta_2');
        scatter(C1plot(1, 1), C1plot(1, 2), '*', 'k');
        hold on
        scatter(C2plot(1, 1), C2plot(1, 2), '*', 'k');
        hold on
        title('curves')
        axis tight
        axis equal
        axis off
        pause(0.01);
        error('sdf');
    end
%%=================== CD method=============================
    rotated = 0;
    isclosed = 0;
    onlyDP = 0;
    auto = 2;
%     [XQNopt, swap2, QNfopts, QNcomtime] = TestElasticCurvesRO(C1, C2, w, rotated, isclosed, onlyDP, 4, auto);
% C1
% C2
    [XQNopt, swap2, QNfopts, QNcomtime] = TestElasticCurvesRO(C1, C2, w, rotated, isclosed, onlyDP, 4, 'LRBFGS', auto);

%%==================plot gamma function=================================  
d = size(C1, 2);
    QNm = XQNopt(end)
    O = XQNopt(end - d*d : end - 1);
    QNO = reshape(O, d, d);
    QNgamma = XQNopt(1 : end - d * d - 1);
    QNgamma%%---
    figure(11);
    plot(1:length(QNgamma), QNgamma);%%---
%     QNgamma = (QNgamma - QNgamma(1)) / (QNgamma(end) - QNgamma(1));
%     XQNopt
    if(swap2)
        QNgamma = invertGamma(QNgamma);
        QNO = QNO';
        QNm = 1 - QNm;
    end
    
%     gammaQN = mod(QNgamma + QNm, 1);
    gammaQN = QNgamma;% modified
    N = length(gammaQN);
    swap2
    
    if(isplot == 1)
        subplot(1, 4, 2);
        scatter((0 : N - 1) / (N - 1), gammaQN, '.', 'r');
        axis([0, 1, 0, 1])
        title('\gamma functions');
        axis equal
        axis([0, 1, 0, 1])
    end

    
    ppC2 = spline((0:(N-1))/(N-1), C2');
    newC2 = zeros(N, d);
    for i = 1 : N
        VV = ppval(ppC2, gammaQN(i));
        newC2(i, :) = VV;
    end
%     C2
%     newC2
    
    if(isplot == 1)
%%==================plot matching curves=================================
        subplot(1, 4, 3);
        plot_matching_curves(C1', C2', gammaQN, 1, strcat('R, L:', num2str(QNfopts(1))), newC2);
        QNcomtime
    end
end

function gamI = invertGamma(gam)
% gam
    N = length(gam);
    x = [0:(N-1)]/(N-1);
    gamI = interp1(gam,x,x,'linear');
% gamI
end

function plot_matching_curves(C1, C2, gamma, L, titlestr, newC2)
%     C1 = Center_C(C1')';
%     C1 = C1 / norm(C1);
%     C2 = Center_C(C2')';
%     C2 = C2 / norm(C2);
%     C2(1, :) = C2(1, :) - min(C2(1, :)) + max(C1(1, :)) + 0.02;

    N = size(C1, 2);
    color_hsv = hsv(N);
    plot(C1(1, :), C1(2, :), 'b')
    hold on
    plot(C2(1, :), C2(2, :), 'r')
    title(titlestr)
    axis equal
    axis tight
    ppC2 = spline((0:(N-1))/(N-1) * L, C2);
    for i = 1 : N
%         VV = ppval(ppC2, gamma(i));
%         scatter([C1(1, i), VV(1)], [C1(2, i), VV(2)], 10, [color_hsv(i, :)]);
%         if(i == 1)
%             scatter([C1(1, i), VV(1)], [C1(2, i), VV(2)], '*', 'k');
%         end
        scatter([C1(1, i), newC2(i, 1)], [C1(2, i), newC2(i, 2)], 10, [color_hsv(i, :)]);
        if(i == 1)
            scatter([C1(1, i), newC2(1, 1)], [C1(2, i), newC2(1, 2)], '*', 'k');
        end
    end
    axis off
end

function output = ReSampleOpenCurve(C, NN)
    N = size(C, 2);
    del(1) = 0;
    for i = 2 : N
        del(i) = norm(C(:, i) - C(:,i - 1));
    end
    cumdel = cumsum(del) / sum(del);
    newdel = [0:NN-1]/(NN-1);
    output(1,:) = spline(cumdel,C(1,1:N),newdel);
    output(2,:) = spline(cumdel,C(2,1:N),newdel);
end

function plot_geodesic(q1, q2n, p2n, stp, str)
    alpha = geodesic_sphere_Full(q1,q2n,stp);

    Path_Plot(alpha,p2n,10,'b',[73,6]);
    axis xy; view([1 90]);
    title(str);
end

% This function calculates a geodesic on the sphere at the
% starting point x_init in the direction g
function X = geodesic_sphere_Full(q1,q2,stp)
    theta = acos(InnerProd_Q(q1,q2));
    if theta > 0.0001
        for t=1:stp+1
            tau = (t-1)/stp;
            X(:,:,t) = (sin((1-tau)*theta)*q1 + sin(tau*theta)*q2)/sin(theta);
            X(:,:,t) = ProjectC(X(:,:,t));
            X(:,:,t) = X(:,:,t)/sqrt(InnerProd_Q(X(:,:,t),X(:,:,t)));
        end
    else
        for t=1:stp+1
            X(:,:,t) = q1;
        end
    end
end

function [qnew] = ProjectC(q)

    [n,T] = size(q);
    if(n == 2)
        dt = 0.35;
    end
    if(n == 3)
        dt = 0.2;
    end
    epsilon = 1e-6;

    e = eye(n);
    iter = 1;
    res = ones(1,n);
    J = zeros(n,n);

    s = linspace(0,1,T);

    qnew = q;
    qnew = qnew/sqrt(InnerProd_Q(qnew,qnew));

    C = [];
    while (norm(res) > epsilon)
        if(iter > 300)
            iter
            break;
        end
        % Compute Jacobian
        for i = 1:n
            for j = 1:n
                J(i,j) = 3 * trapz(s,qnew(i,:) .*qnew(j,:) );
            end
        end
        J = J+ eye(size(J));


        for i = 1:T
            qnorm(i) = norm(qnew(:,i));
        end

        %%%%%%%%%%%%%%%%
        % Compute the residue
        for i = 1:n
            G(i) = trapz(s,qnew(i,:).*qnorm);
        end
        res = -G;

        if(norm(res) < epsilon)
            break;
        end

    %     %qnew
    %     tmp = isnan(J());
    %     tmp
    %     J
    %     if sum(sum(tmp)) > 0
    %     %if min(svd(J)) < 0.0001
    %         keyboard;
    %     end

        x = inv(J)*res';
        C(iter) = norm(res);

        %x = regress(res',J);
        delG = Form_Basis_Normal_A(qnew);
        temp = 0;
        for i = 1:n
            temp = temp + x(i)*delG{i}*dt;
        end
        qnew = qnew + temp;
    %     keyboard;
        %qnew = qnew/sqrt(InnerProd_Q(qnew,qnew));
        iter = iter + 1;
    end

    % keyboard;

    %%%%%%%%%%%%%%%%
    qnew = qnew/sqrt(InnerProd_Q(qnew,qnew));

    %figure(56);
    %plot(C);
end

function delG = Form_Basis_Normal_A(q)
    [n,T] = size(q);
    e = eye(n);
    for i = 1:n
        Ev(:,:,i) = repmat(e(:,i),1,T);
    end

    for t = 1:T
        qnorm(t) = norm(q(:,t));
    end

    for i = 1:n
        tmp1 = repmat(q(i,:)./qnorm,n,1);
        tmp2 = repmat(qnorm,n,1);
        delG{i} = tmp1.*q + tmp2.*Ev(:,:,i);    
    end

    return;
end

function Path_Plot(alpha,p2n,fig,col,view_az_el)
    colorspecstr = ['b','m','k','r','g','b','k','m','g','r','g','b','k'];
    [n,T,k] = size(alpha);
    stp = k-1;
    if(n == 3)
        dt = 0.10;
    else
        dt = 0.07;
    end
%     figure(fig); clf;
    axis ij;
    for tau = 1:stp+1
        if (tau==stp+1)
            p = q_to_curve(alpha(:,:,tau));
            normp = norm(Center_C(p')');
            p = Center_C(p2n')';
            p = p / norm(p) * normp;
%             p = p2n;
        else
            p = q_to_curve(alpha(:,:,tau));
            p = Center_C(p')';
        end
%         figure(tau + 10);%------
%         size(alpha(:,:,tau))
%         size(p)
%         plot(p(1, :), p(2, :));%--------
        
%         p = p / norm(p);

    %     ft = p;
    %     ft(1,:) = p(1,:) + dt*tau;
        
        %% plot geodesic vertically
%         ft(2,:) = p(2,:) ;
%         ft(2,:) = ft(2,:) - mean(ft(2,:)) +dt*tau;
%         for i = 1:1
%             ft(i,:) = p(i,:) - mean(p(i,:));
%         end
        
        %% plot geodesic horizontally
        ft(1,:) = p(1,:) ;
        ft(1,:) = ft(1,:) - mean(ft(1,:)) +dt*tau;
        for i = 2:n
            ft(i,:) = p(i,:) - mean(p(i,:));
        end
        if(n == 2)
%             z = plot(ft(1,:),ft(2,:),'Color',colorspecstr(tau),'LineWidth',1); axis equal; hold on;axis equal; axis off; axis tight;  
%             set(z,'LineWidth',[3]);
            
            N = size(ft, 2);
            color_hsv = hsv(N);
            for i = 2 : N
                z = scatter(ft(1, i), ft(2, i), 100, [color_hsv(i, :)], '.');
                hold on
            end
            axis equal; hold on;axis equal; axis off; axis tight;  

        else
            if(n == 3)
                f1(1,:,tau) = ft(1,:);
                f1(2,:,tau) = ft(2,:);
                f1(3,:,tau) = ft(3,:);
                 plot3(ft(1,:),ft(2,:),ft(3,:),'Color',colorspecstr(tau),'LineWidth',1.5); axis equal; hold on;axis equal; axis off; axis tight;             
                view(view_az_el(1),view_az_el(2));axis on;
                set(gca,'XTickLabel','','YTickLabel','','ZTickLabel','');     % Remove the tick labels
                set(gca,'TickLength',[0 0]);                  % Remove the ticks
            end
        end
    end
    % print(gcf,'-depsc2', 'geodesic.eps');
    axis ij;
end

function p = q_to_curve(q)
    [n,T] = size(q);
    for i = 1:T
        qnorm(i) = norm(q(:,i),'fro');
    end
    for i = 1:n
        p(i,:) = [ cumtrapz( q(i,:).*qnorm )/(T) ] ;
    end
end

function [q] = curve_to_q(p)
    [n,N] = size(p);
    xslope = (p(1, 2) - p(1, end - 1)) / (2 / (N - 1));
    yslope = (p(2, 2) - p(2, end - 1)) / (2 / (N - 1));
    pp = zeros(n, N + 2);
    pp(1, 1) = xslope;
    pp(1, end) = xslope;
    pp(2, 1) = yslope;
    pp(2, end) = yslope;
    pp(:, 2 : end - 1) = p;
    
    ppp = spline((0 : (N - 1)) / (N - 1), pp);
    ppdp = first_deri(ppp);
    v = ppval(ppdp, (0 : (N - 1)) / (N - 1));
    for i = 1:N
        L(i) = sqrt(norm(v(:,i),'fro'));
        if L(i) > 0.000001
            q(:,i) = v(:,i)/L(i);
        else
            q(:,i) = 0*ones(n,1);
        end
    end
    q = q/sqrt(InnerProd_Q(q,q));
end

function val = InnerProd_Q(q1,q2)
    [n,T] = size(q1);
    val = trapz(linspace(0,2*pi,T),sum(q1.*q2));
end

function output = first_deri(ppf)
    ppf.coefs = ppf.coefs(:, 1 : 3);
    ppf.coefs(:, 1) = ppf.coefs(:, 1) * 3;
    ppf.coefs(:, 2) = ppf.coefs(:, 2) * 2;
    ppf.order = 3;
    output = ppf;
end

function output = Center_C(C)
    C = C';
    [n, N] = size(C);
    ave = sum(C')' / N;
    output = C;
    for i = 1 : N
        output(:, i) = output(:, i) - ave;
    end
    output = output';
end

function output = shift_C(C, i)
    output = zeros(size(C));
    output(:, end - i + 1 : end) = C(:, 1 : i);
    output(:, 1 : end - i) = C(:, i + 1 : end);
    
    return;
    output = zeros(size(C));
    output(:, 1 : i) = C(:, end - i + 1 : end);
    output(:, i + 1 : end) = C(:, 1 : end - i);
end
