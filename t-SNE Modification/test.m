% X = load('wilt.mat');
% 
% test1 = mvnrnd([0 0 0],(10^-1) * eye(3),100);
% test2 = mvnrnd([1 1 1],(10^-1) * eye(3),100);
% data_test = [test1;test2];

% figure(1)
% plot(data_test(:, 1), data_test(:, 2), 'o');

% [sig,perp] = find_perp(data_test);
% [sig,perp] = find_perp(data_test(1:4, :));

% Y = [mvnrnd([0 0],(10^-1) * eye(2),100);mvnrnd([1 1],(10^-1) * eye(2),100)];
% result = mytsne(data_test(1:4, :),sig(1:4),100);
[result,P] = testtsne(data_test,[],Y,3,30);


figure(2)
scatter(result(:,1),result(:,2));

%they change perpelxity in d2p
%update loop looks different, they calculate p values