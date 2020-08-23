%creates a matrix of citation data (x axis is papers and y axis is years
%after publication). The data for the year of publication is ommited. Only
%the second and third year are outputted as a matrix.
function mat = short_matrix(cconference, cyear, data)
    m = tomatrix(cconference,cyear,data);
    s = size(m);
    
    if s(1) < 3
        mat = m(end,:);
    else
        mat = m(1:2,:); % selecting 1st and 2nd row because the first row in
                        % the original matrix is removed in the 'tomatrix' function
    end
end