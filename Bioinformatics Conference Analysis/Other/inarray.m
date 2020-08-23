%checks to see if an object in in an array
function y = inarray(object, array)
    temp_array = ismember(object, array);
    
    for i = 1:length(temp_array)
        if temp_array(i) == 1
            y = true;
        else
            y = false;
        end
    end
end