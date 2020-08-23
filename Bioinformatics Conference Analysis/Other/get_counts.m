%creates an array of counts given a conference struct
function y = get_counts(struct)
    
    for i = 1:length(struct)
        array(i) = struct(i).count;
    end
    y = array;
end