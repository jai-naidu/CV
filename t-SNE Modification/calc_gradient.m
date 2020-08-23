function gradient = calc_gradient(pij,qij,Y)
    temp = zeros(size(Y,1),size(Y,2));

    for i = 1:size(Y,1)
        for j = 1:size(Y,1)           
            temp(i,:) = temp(i,:) + ((qij(i,j)*(pij(i,j) - qij(i,j))*...
                ((1 + (norm(Y(i,:)-Y(j,:)).^2))^(-1))).*(Y(i,:)-Y(j,:)));
        end
        gradient = 4 .* temp;
    end
end