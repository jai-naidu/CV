%loops through the citation data json file and adds a value '0' to the
%'citations' dictionary for years that papers did not recieve citations
function y = fill_dates(unfilled_data)
    for i = 1:length(unfilled_data)
        temp_keys = string(fieldnames(unfilled_data(i).citations));

        for j = 1:((2017 - unfilled_data(i).year) + 1)
            temp = unfilled_data(i).year - 1;
                
            if ~(inarray(strcat('x', num2str(temp + j)), temp_keys))
                unfilled_data(i).citations.(strcat('x', (num2str(temp + j)))) = 0;
            end  
        end
    end
    
    y = unfilled_data;
end