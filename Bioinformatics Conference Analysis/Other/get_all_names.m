%creates an array of all author names given the json data with no repeats
function y = get_all_names(data)
    counter = 1;
    array = [" "];

    for i = 1:length(data)
        for j = 1:length(data(i).authors)
            
            if ~(inarray(shortname(string(data(i).authors(j))),array))
                array(counter) = shortname(string(data(i).authors(j)));
                counter = counter + 1;
            else
                continue
            end
        end
    end
    y = sort(array);
end