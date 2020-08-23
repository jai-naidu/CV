function [sigma,perplexity] = find_perp(X)
    
    for i = 1:size(X,1)       
        base = 0.001;
        temp_sig = 0.001;
        temp_perp = 0;
        perp_test = -1;
        
        while (temp_perp < 30 || temp_perp > 30.0001) || isnan(temp_perp)
            
            temprow = calc_pji_row(X,temp_sig,i);
            temp_perp = calc_perp_row(temprow);
            old_sig = temp_sig - base;
            temp_sig = temp_sig + base;
            
            if perp_test ~= -1 && (temp_perp > 30.0001 && temp_perp > perp_test)
                base = base/10;
                temp_sig = old_sig;
            end
            perp_test = temp_perp;
        end
        
        perplexity(i) = temp_perp;
        sigma(i) = temp_sig;
    end
end