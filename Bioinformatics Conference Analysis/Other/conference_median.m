%finds the median value of the column averages
function y = conference_median(matrix)
    y = median(mean(matrix,1)); % mean for every paper and then find median paper
end