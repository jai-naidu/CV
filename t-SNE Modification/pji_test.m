function pji_num = pji_test(X)
    P = zeros(size(X,1),size(X,1));
    pji_num = zeros(size(X,1),size(X,1));

    for i = 1:size(X,1)
        for j = 1:size(X,1)
            if i ~= j
%                 pji_num(i,j) = exp((0-(norm(X(i,:)-X(j,:))^2))/(2*sig(i)^2));
                  pji_num(i,j) = norm(X(i,:)-X(j,:))^2;
            else
                pji_num(i,j) = 0;
            end
        end
%         temp = sum(pji_num(i,:));
%         
%         for j = 1:size(X,1)
%             P(i,j) = pji_num(i,j)/temp;
%         end
    end  
end