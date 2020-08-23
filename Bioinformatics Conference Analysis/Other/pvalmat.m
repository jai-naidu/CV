%creates a matrix of p values for each conference using the binomial test
function y = pvalmat(graph_data)

    for i = 1:6
        for j = 1:6
            if i == j
                matrix(i,j) = 0;
            else
                [k, n] = get_wins(i,j,graph_data);
                matrix(i,j) = 1-binocdf(k-1,n,0.5);
            end
        end
    end
    
    y = matrix;
end