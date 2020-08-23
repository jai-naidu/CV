%calculates the average number of authors per paper
function y = av_auth(data,conf_array)
    denom = 0;
    counter = 0;

    for i = 1:length(data)
        if inarray(data(i).conference, conf_array)
            denom = denom + 1;
            
            for j = 1:length(data(i).authors)
                counter = counter + 1;
            end
        else
            continue
        end
    end
    y = counter/denom;
end