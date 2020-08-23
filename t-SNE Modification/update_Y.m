function [Y,Ym1,Ym2] = update_Y(Ym1,Ym2,alpha,n,gradient,t)
    
    if t == 1
        temp = Ym1 + n .* gradient;
        
        Ym1 = temp;
        Ym2 = Ym1;
        Y = temp;
    else
        temp = Ym1 + n .* gradient + alpha .* (Ym1 - Ym2);
        
        Ym1 = temp;
        Ym2 = Ym1;
        Y = temp;
    end
end