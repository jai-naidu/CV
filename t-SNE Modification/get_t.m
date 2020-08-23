function alpha = get_t(t)
    if t < 250
        alpha = 0.5;
    elseif t >= 250
        alpha = 0.8;
    end
end