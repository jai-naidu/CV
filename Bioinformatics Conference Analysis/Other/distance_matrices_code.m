% reading json data
unfilled_data = jsondecode(fileread('Final_Citation_Data.json'));
data = fill_dates(unfilled_data);

%finds the set union of all of the names in all of the conferences
all_names = get_all_names(data);

% calculates the yang distance between conferences and creates a matrix
% representation
yang_mat = nm_distance_mat(data,all_names);

% calculates the jaccard distance between conferences and creates a matrix
% representation
jac_mat = jac_mat(data);

% create dendrograms for the different matrices
figure(1)
jm = linkage(jac_mat);
dendrogram(jm);

figure(2)
yd = linkage(yang_mat);
dendrogram(yd);