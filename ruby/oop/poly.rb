
require_relative '../can_run'

# OOP example in ruby
#
# see also:
# http://thoughts.codegram.com/understanding-class-instance-variables-in-ruby/
# see also:
# http://www.railstips.org/blog/archives/2006/11/18/class-and-instance-variables-in-ruby/
class Poly
  include CanRun

  # Constructor in ruby
  def initialize()
    Poly.static_check
    puts "(instance) Constructing class <#{self.class}>"
  end
end
