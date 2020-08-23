%finds the count of an author in a conf_struct
function y = find_count(name,conf_struct,names) 

    for i = 1:length(conf_struct)
        if inarray(name,names)
            y = conf_struct(find(name==names)).count;
        else
            y = 0;
        end
    end
end