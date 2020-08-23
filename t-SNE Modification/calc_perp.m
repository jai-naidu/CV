function perp = calc_perp(pji)
    perp = zeros(1,length(pji));
    
    for i = 1:length(pji)
        temp = 0 - (sum(pji(i,:).*logb2(pji(i,:))));
        perp(i) = 2^temp; 
    end   
end