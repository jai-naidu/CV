function sigma = find_sigs(X,perp,precision)
    sig_range = (0.0001:0.0001:100);

    for i = 1:size(X,1)       
        
        while round(temp_perp,precision) ~= perp || isnan(mid_perp)
            
            low_pji = calc_pji_row(X,sig_range(1),i);
            high_pji = calc_pji_row(X,sig_range(length(sig_range)),i);
            mid_pji = calc_pji_row(X,sig_range(round((length(sig_range))/2)),i);
            
            low_perp = calc_perp_row(low_pji);
            high_perp = calc_perp_row(high_pji);
            mid_perp = calc_perp_row(mid_pji);
            
            if (perp - low_perp) < (perp - high_perp)
            elseif (perp - high_perp) < (perp - low_perp)
            elseif high - low == 1 || high - low == 0
            end
            
        end
    end
    sigma(i) = temp_sig;
end