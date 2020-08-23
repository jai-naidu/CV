function perp = calc_perp_row(row)

        temp = 0 - (sum(row.*logb2(row)));
        perp = 2^temp; 
end