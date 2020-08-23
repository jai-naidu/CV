%finds the median value of the top ten values of each column average
function y = conference_top10(matrix)
    y = median(maxk(mean(matrix,1), 10));
end