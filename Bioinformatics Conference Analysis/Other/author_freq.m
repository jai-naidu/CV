%creates a struct that consists of the name of an author and how many times
%they published in a specified conference within a specified year range
function y = author_freq(data,conf_array,years)
    counter = 1;

    for i = 1:length(data)
        if inarray(data(i).conference,conf_array) && inarray(data(i).year,years)
            
            for j = 1:length(data(i).authors)
                
                if counter > 1
                    temp = get_names(auth_reps);
                else
                    temp = ["place","holder"];
                end
                
                if inarray(shortname(string(data(i).authors(j))),temp)
                    auth_reps(find(get_names(auth_reps)==shortname(string(data(i).authors(j))))).count =...
                        auth_reps(find(get_names(auth_reps)==shortname(string(data(i).authors(j))))).count + 1;               
                else
                    auth_reps(counter).name = shortname(string(data(i).authors(j)));
                    auth_reps(counter).count = 1;
                    counter = counter + 1;
                end
            end
        else
            continue
        end
    end
    y = auth_reps;
end