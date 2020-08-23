X = load('wilt.mat');

test1 = mvnrnd([0 0 0],(10^-1) * eye(3),100);
test2 = mvnrnd([1 1 1],(10^-1) * eye(3),100);
data_test = [test1;test2];

figure(1)
plot(data_test(:, 1), data_test(:, 2), 'o');

[sig,perp] = find_perp(data_test);

result = tsne(data_test(1:4, :),sig(1:4),100);

figure(2)
scatter(result(:,1),result(:,2));