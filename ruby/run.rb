#!/usr/local/bin/ruby

require_relative 'oop/poly'

can_runs = []

can_runs << Poly.new

can_runs.each do |can_run|
  can_run.run

  puts
end
