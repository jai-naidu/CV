% converts strings in a given array to numbers

function y = string_to_num(array)
    for i = 1:length(array)
        output(i) = str2num(array(i));
    end
    y = output;
end