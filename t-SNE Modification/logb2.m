function newrow = logb2(row)
    for i = 1:length(row)
        if row(i) == 0
            newrow(i) = 0;
        else
            newrow(i) = log2(row(i));
        end
    end
end