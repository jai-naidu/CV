%finds the set intersect of multiple sets
function [A,ia,varargout] = intersectm(A,varargin)
varargout = cell(size(varargin));
ia = 1:numel(A);
for ii = 1:numel(varargin)
    [A,ixa,varargout{ii}] = intersect(A,varargin{ii});
    ia = ia(ixa);
    for jj = 1:ii-1
        varargout{jj} = varargout{jj}(ixa);
    end
end
end