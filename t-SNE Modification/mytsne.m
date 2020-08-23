function Y = mytsne(X,sig)
    pij = zeros(size(X,1),size(X,1));

    pji = calc_pji(X,sig);
    
    for i = 1:size(X,1)
        for j = 1:size(X,1)
            if i == j
                pij(i,j) = 0;
            else
                pij(i,j) = (pji(i,j) + pji(j,i))/(2*size(X,1));
            end
        end
    end
    
    Y = mvnrnd([0 0],(10^-1) * eye(2),round(size(X,1)/2));
    Y = [Y;mvnrnd([1 1],(10^-1) * eye(2),round(size(X,1)/2))];
    Ym1 = 0;
    Ym2 = 0;
    dbt = zeros(size(X,1),size(Y,2));
    eta = 0.2 * (ones(size(Y,1),size(Y,2)));
    
    num = 1;
    ctest = 1;
    t = 1;
    while num > 10^(-12)
%         plot(Y(1:100,1),Y(1:100,2),'o');
%         hold on;
%         plot(Y(101:200,1),Y(101:200,2),'o');
%         hold off;
        qij = calc_qij(Y);
        gradient = calc_gradient(pij,qij,Y);
%         [eta,dbt] = get_n(gradient,eta,0.5,0.8,100,dbt);
        [Ynew,Ym1,Ym2] = update_Y(Ym1,Ym2,get_t(t),eta,gradient,t);
        
        ctest = abs(Ynew - Y);
        num = sum(sum(ctest)) / size(Y,1);
        t = t+1;
        Y = Ynew;
    end
end