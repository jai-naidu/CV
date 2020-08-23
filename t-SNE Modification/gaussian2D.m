function gaussian = gaussian2D(num_points)
    m = [0 0]';
    S = [10^-4 0; 0 10^-4];
    n = num_points;
    gaussian = repmat(m', n, 1) + randn(n, 2) * chol(S);
end