%finds the standard deviation given a matrix of conference citations
function sd = get_std(conf_matrix,type)

    if type == "av"
        for i = 1:1000
            array(i) = conference_average(bootstrap(conf_matrix));
        end
    elseif type == "med"
        for i = 1:1000
            array(i) = conference_median(bootstrap(conf_matrix));
        end
    elseif type == "top"
        for i = 1:1000
            array(i) = conference_top10(bootstrap(conf_matrix));
        end
    end
    
    sd = std(array);
end