%creates a matrix of the yang distance between each conference according to
%the number of times authors appear in conferences
function m = nm_distance_mat(data,all_names)
    temp_conf = jsondecode(fileread('conf_data.json'));
    m = zeros(5,5);

    for i = 1:5
        for j = 1:5
            if i == j
                m(i,j) = 0;
            else
                years = get_year_range(temp_conf(i).conf,temp_conf(j).conf);
                
                c1 = filtered_auth_counts(all_names,data,years,temp_conf(i).conf);
                c2 = filtered_auth_counts(all_names,data,years,temp_conf(j).conf);
                
                [~, m(i,j)] = yangdistance(c1,c2,2);
            end
        end
    end
end