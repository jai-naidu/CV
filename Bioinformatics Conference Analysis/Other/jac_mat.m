%creates a matrix of the jaccard distance between each conference
function m = jac_mat(data)
    temp_conf = jsondecode(fileread('conf_data.json'));
    m = zeros(5,5);

    for i = 1:5
        for j = 1:5
            
            if i == j
                m(i,j) = 0;
            else
                years = get_year_range(temp_conf(i).conf,temp_conf(j).conf);
                c1 = author_freq(data,celltomat(temp_conf(i).conf),years);
                c2 = author_freq(data,celltomat(temp_conf(j).conf),years);
                
                m(i,j) = 1 - (length(intersect(get_names(c1),...
                    get_names(c2)))/length(union(get_names(c1),...
                    get_names(c2))));
            end
        end
    end
end