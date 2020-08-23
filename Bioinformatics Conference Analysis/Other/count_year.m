%counts how many papers fit within the specified year and conference
%parameters
function y = count_year(year, conference, data)
    
    counter = 0;

    for i = 1:length(data)
        if data(i).year == year && string(data(i).conference) == string(conference)
            counter = counter + 1;
        end
    end
    
    y = counter;
end