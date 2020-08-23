%converts a cell to a matrix
function y = celltomat(cell)
    for i = 1:length(cell)
        array(i) = string(cell(i));
    end
    y = array;
end