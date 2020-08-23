%outputs a shortened version of a given name (*first letter of first name*
%*last name*)
function y = shortname(string)
    loe = ["I","II","III","Jr.","Jr","jr.","jr"];
    loo = ["consortium", "Consortium","initiative","Initiative"];
    temp = strsplit(string);
    firstname = char(temp(1));
    
    if inarray(temp(length(temp)), loe)
        y = strcat(firstname(1)," ",temp(length(temp) - 1));
    elseif inarray(temp(length(temp)), loo)
        y = "Groups";
    else
        y = strcat(firstname(1)," ",temp(length(temp)));
    end
end