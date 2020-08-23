%creates an array for the number of author appearances in the same order as 'names' array
function y = filtered_auth_counts(names,data,years,conf)
    array = zeros([1 length(names)]);

    for i = 1:length(data)
        if inarray(data(i).year,years) && inarray(data(i).conference,conf)
            
            for j = 1:length(data(i).authors)
                if inarray(shortname(string(data(i).authors(j))),names)
                    index = find(names==shortname(string(data(i).authors(j))));
                    array(index) = array(index) + 1;
                end
            end
        else
            continue
        end
    end
    y = array;
end