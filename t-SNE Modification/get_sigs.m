function sigmas = get_sigs(perp_dict)
    for i = 1:length(perp_dict)
        sigmas = zeros(1,length(perp_dict));
        sigmas(i) = perp_dict(i).sigma;
    end
end