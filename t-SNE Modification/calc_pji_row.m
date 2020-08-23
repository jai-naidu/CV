function P = calc_pji_row(X,sig,row)
    P = zeros(1,size(X,1));
    pji_num = zeros(1,size(X,1));

    for i = row:row
        for j = 1:size(X,1)
            if i ~= j
                pji_num(j) = exp((0-(norm(X(i,:)-X(j,:))^2))/(2*sig^2));
            else
                pji_num(j) = 0;
            end
        end
        temp = sum(pji_num);
        
        for j = 1:size(X,1)
            P(j) = pji_num(j)/temp;
        end
    end  
end