%returns all possible combinations of a set with repition (not my function)
function combs = nmultichoosek(values, k)
    if numel(values)==1 
        n = values;
        combs = nchoosek(n+k-1,k);
    else
        n = numel(values);
        combs = bsxfun(@minus, nchoosek(1:n+k-1,k), 0:k-1);
        combs = reshape(values(combs),[],k);
end