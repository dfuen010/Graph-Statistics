-module(graph_stats).
-export([start/3]).

start(Input_file_path, Part_a_output_file_path, Part_b_output_file_path) ->
    parse_input_file(Input_file_path).

parse_input_file(FilePath) ->
    {ok, Input} = file:read_file(FilePath),
    Partitions = parse_input(Input),
    Partitions.

parse_input(Input) ->
    Lines = binary:split(Input, [<<"\r\n">>, <<"\n">>, <<"\r">>], [global]),
    parse_partitions(Lines).

parse_partitions(Input) ->
    Lines = re:split(binary_to_list(Input), "\r\n|\n|\r", [{return, list}]),
    parse_partitions(Lines, [], false).

parse_partitions(["partition " ++ PNum | Rest], Acc, true) ->
    {Nodes, Rest1} = parse_nodes(Rest, []),
    {Colors, Rest2} = parse_colors(Rest1, []),
    {Edges, Rest3} = parse_edges(Rest2, []),
    Partition = #{partition_number => binary:decode_integer(PNum),
                 nodes => Nodes,
                 colors => Colors,
                 edges => Edges},
    parse_partitions(Rest3, [Partition | Acc], false).

parse_partitions(Lines, Acc) when is_list(Lines) ->
    parse_partitions(Lines, [], false).

parse_nodes([Line | Rest], Acc) ->
    NodeList = re:split(Line, ",", [{return, list}]),
    NodeIntList = [list_to_integer(NodeStr) || NodeStr <- NodeList],
    parse_nodes(Rest, NodeIntList ++ Acc).

parse_colors([Line | Rest], Acc) ->
    ColorList = re:split(Line, ",", [{return, list}]),
    parse_colors(Rest, ColorList ++ Acc).

parse_edges([Line | Rest], Acc) ->
    [Node1Str, Node2Str | _] = re:split(Line, ",", [{return, list}]),
    Node1 = list_to_integer(Node1Str),
    Node2 = list_to_integer(Node2Str),
    parse_edges(Rest, [{Node1, Node2} | Acc]).