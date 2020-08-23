%creates an array of author names in a conference struct
function y = get_names(struct)
    
    for i = 1:length(struct)
        array(i) = struct(i).name;
    end
    y = array;
end