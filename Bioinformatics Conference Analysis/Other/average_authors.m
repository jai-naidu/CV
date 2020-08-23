% unfilled_data = jsondecode(fileread('Final_Citation_Data.json'));
% data = fill_dates(unfilled_data);

%prints the average number of authors in total and per conference
fprintf("Average Number of Authors: %f\n", av_auth(data,["ISMB","ISMB-ECCB","ECCB","PSB","BCB","RECOMB"]));
fprintf("");
fprintf("Average Number of Authors (ISMB with ISMB-ECCB): %f\n", av_auth(data,["ISMB","ISMB-ECCB"]));
fprintf("Average Number of Authors (ISMB): %f\n", av_auth(data,["ISMB"]));
fprintf("Average Number of Authors (ECCB): %f\n", av_auth(data,["ECCB"]));
fprintf("Average Number of Authors (PSB): %f\n", av_auth(data,["PSB"]));
fprintf("Average Number of Authors (BCB): %f\n", av_auth(data,["BCB"]));
fprintf("Average Number of Authors (RECOMB): %f\n", av_auth(data,["RECOMB"]));