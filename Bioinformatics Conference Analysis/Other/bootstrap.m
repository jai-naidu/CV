%Bootstrap samples given matrix
function y = bootstrap(conf_matrix)
    
    ind = randi([1 length(conf_matrix)],1,length(conf_matrix));
    for i = 1 : length(conf_matrix)
        M(:, i) = conf_matrix(:, ind(i));
    end
    y = M;
end