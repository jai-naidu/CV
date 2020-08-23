function qij = calc_qij(gaussian)
    qij = zeros(size(gaussian,1),size(gaussian,1));
    denom = 0;
    
    for i = 1:size(gaussian,1)
        for j = 1:size(gaussian,1)
            if i ~= j
                qij(i,j) = (1+(norm(gaussian(i,:)-gaussian(j,:))^2))^(-1);
                denom = denom + (1+(norm(gaussian(i,:)-gaussian(j,:))^2))^(-1);
            else
                continue
            end
        end
    end
    
    for k = 1:size(gaussian,1)
        for l = 1:size(gaussian,1)
            qij(k,l) = qij(k,l)/denom;
        end
    end
end