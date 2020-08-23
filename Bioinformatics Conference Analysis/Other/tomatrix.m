%creates a matrix of citation data (x axis is papers and y axis is years
%after publication). The data for the year of publication is ommited.
function matrix = tomatrix(cconference, cyear, data)
    reset = 0;
    
    for i = 1:(2017 - cyear + 1)
        
        if i > reset
            counter = 1;
            reset = reset + 1;
        end
                
        for j = 1:length(data)
            if data(j).year == cyear && string(data(j).conference) == string(cconference)
                matrix(i,counter) = data(j).citations.(strcat('x', num2str(cyear + i - 1)));
                counter = counter + 1;
            end
        end
    end
    matrix(1,:) = []; % removing row 1
end