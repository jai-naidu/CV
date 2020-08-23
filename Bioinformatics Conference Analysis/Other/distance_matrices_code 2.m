% % reading json data
% unfilled_data = jsondecode(fileread('Final_Citation_Data.json'));
% data = fill_dates(unfilled_data);
% 
% %finds the set union of all of the names in all of the conferences
% all_names = get_all_names(data);
% 
% % calculates the yang distance between conferences and creates a matrix
% % representation
% yang_mat = nm_distance_mat(data,all_names);
% 
% % calculates the jaccard distance between conferences and creates a matrix
% % representation
% jac_mat = jac_mat(data);

% create dendrograms for the different matrices
figure(1)
jm = linkage(jac_mat);
dendrogram(jm);
title('Single_Jaccard');

figure(2)
yd = linkage(yang_mat);
dendrogram(yd);
title('Single_Yang');

figure(3)
jm = linkage(jac_mat, 'average');
dendrogram(jm);
title('Average_Jaccard');

figure(4)
yd = linkage(yang_mat, 'average');
dendrogram(yd);
title('Average_Yang');

figure(5)
jm = linkage(jac_mat, 'complete');
dendrogram(jm);
title('Complete_Jaccard');

figure(6)
yd = linkage(yang_mat, 'complete');
dendrogram(yd);
title('Complete_Yang');

figure(7)
yd = linkage(yang_mat, 'ward');
dendrogram(yd);
title('Ward_Jaccard');

figure(8)
yd = linkage(yang_mat, 'ward');
dendrogram(yd);
title('Ward_Yang');

