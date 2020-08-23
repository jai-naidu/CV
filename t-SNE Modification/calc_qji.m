function Q = calc_qji(gaussian,row)
    Q = zeros(1,size(gaussian,1));
    qji_num = zeros(1,size(gaussian,1));

    for i = row:row
        for j = 1:size(gaussian,1)
            if i ~= j
                qji_num(j) = exp(0-(norm(gaussian(i,:)-gaussian(j,:))^2));
            else
                qji_num(j) = 0;
            end
        end
        temp = sum(qji_num);
        
        for j = 1:size(gaussian,1)
            Q(j) = qji_num(j)/temp;
        end
    end
end